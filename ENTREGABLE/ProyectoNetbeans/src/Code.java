/*
    File: Code.java
    Developer: Guerra Vargas Irving Cristobal
    email: guerravargasirving@gmail.com
*/
import java.awt.Color;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

public class Code {
    
    private int programCounter;
    private final ArrayList  memory;
    private final Stack stack;
    private final Symbol table;
    private boolean stop = false;
    private boolean returning = false;
    private final Stack<Marco> stackMacros;
    private final CurrentState currentState;
    
    public Code(Symbol tabla){
        currentState = new CurrentState();
        programCounter = 0;
        memory = new ArrayList<Method>();
        stack = new Stack();
        this.table = tabla;
        stackMacros = new Stack();
    }
    
    public int numOfElements(){
        return memory.size() + 1;
    }

    public int addOperation(String name){
        int posicion = memory.size();
        try{
            memory.add(this.getClass().getDeclaredMethod(name, null));
            return posicion;
        }
        catch(Exception e ){
            System.out.println("[ ERROR ] Error al agregar la operación " + name);
        }
        return -1;
    }
    
    public int add(Object object){
        int posicion = memory.size();
        memory.add(object);
        return posicion;
    }
    
    public void add(Object object, int pos){
        memory.remove(pos);
        memory.add(pos, object);
    }
    
    public int addOperationIn(String name, int pos){
        try{
            memory.add(pos, this.getClass().getDeclaredMethod(name, null));
        }
        catch(Exception e ){
            System.out.println("[ ERROR ] Error al agregar la operación " + name);
        }
        return pos;
    }
    
    private void SUM(){
        Object matriz2 = stack.pop();
        Object matriz1 = stack.pop();
		stack.push((double)matriz1 + (double)matriz2);
    }
    
    private void RES(){
        Object matriz2 = stack.pop();
        Object matriz1 = stack.pop();
		stack.push((double)matriz1 - (double)matriz2);
    }

    private void MUL(){
        Object matriz2 = stack.pop();
        Object matriz1 = stack.pop();
		stack.push((double)matriz1 * (double)matriz2);
    }
    
    private void negativo(){
        Object matriz1 = stack.pop();
        System.out.println(matriz1);
        stack.push(-(double)matriz1);
    }
    
    private void constPush(){
        stack.push(memory.get(++programCounter));
    }
    
    private void varPush(){
        stack.push(memory.get(++programCounter));
    }
    
    private void varPush_Eval(){
        stack.push(table.lookup((String)memory.get(++programCounter)));
    }

    private void asignar(){
        String variable = (String)stack.pop();
        Object objeto = stack.pop();
        table.insert(variable, objeto);
    }
    
    private void EQ(){
        Object A = stack.pop();
        Object B = stack.pop();
		stack.push((boolean)((double)A==(double)B));
    }

    private void NE(){
        Object A = stack.pop();
        Object B = stack.pop();
		stack.push((double)A!=(double)B);
    }

    private void LE(){
        double a;
        double b;
        Object B = stack.pop();
        Object A = stack.pop();
		a = (double)A;
		b = (double)B;
        stack.push(a < b);
    }

    private void GR(){
        double a;
        double b;
        Object B = stack.pop();
        Object A = stack.pop();
		a = (double)A;
		b = (double)B;
        stack.push(a > b);
    }

    private void LQ(){
        double a;
        double b;
        Object B = stack.pop();
        Object A = stack.pop();
		a = (double)A;
		b = (double)B;
        stack.push(a <= b);
    }

    private void GE(){
        double a;
        double b;
        Object B = stack.pop();
        Object A = stack.pop();
		a = (double)A;
		b = (double)B;
        stack.push(a >= b);
    }

    private void NOT(){
        stack.push(! (boolean)stack.pop());
    }

    private void AND(){
        stack.push((boolean)stack.pop() && (boolean)stack.pop());
    }

    private void OR(){
        stack.push((boolean)stack.pop() || (boolean)stack.pop());
    }
    
    private void stop(){
        stop = true;
    }
    
    private void _return(){
        returning = true;
    }

    private void nop(){
    }
    
    private void WHILE(){
        int condicion = programCounter;
        boolean continua = true;
        while(continua && !returning){
            ejecutar(condicion + 3);           
            if((boolean)stack.pop()){ 
                ejecutar((int)memory.get(condicion+1));
            }
            else{
                programCounter = (int)memory.get(condicion+2); 
                continua = false;
            }
        }     
    }
    
    private void IF_ELSE(){
        int condicion = programCounter;
        ejecutar(condicion + 4); 
        boolean resultado = true;
        try{
            resultado = (boolean)stack.pop();
        }
        catch(Exception e ){
        }
        if(resultado){
            ejecutar((int)memory.get(condicion+1));
        }
        else{
            ejecutar((int)memory.get(condicion+2));
        }
        programCounter = (int)memory.get(condicion+3) - 1; 
    }

    private void FOR(){
        int condicion = programCounter;
        ejecutar(condicion + 5);  
        boolean continua = true;
        while(continua && !returning){
            ejecutar((int)memory.get(condicion+1)); 
            if((boolean)stack.pop()){ 
                ejecutar((int)memory.get(condicion+3));
                ejecutar((int)memory.get(condicion+2));
            }
            else{
                programCounter = (int)memory.get(condicion+4); 
                continua = false;
            }
        } 
    }
    
    private void declaracion(){
        table.insert((String)memory.get(++programCounter), ++programCounter); 
        int invocados = 0;
        while(memory.get(programCounter) != null || invocados != 0){ 
            if( memory.get(programCounter) instanceof Method)
                if(((Method)memory.get(programCounter)).getName().equals("invocar"))
                    invocados++;
            if( memory.get(programCounter) instanceof Funcion)
                invocados++;
            if(memory.get(programCounter) == null)
                invocados--;
            programCounter++;
        }
    }
    
    private void invocar(){   
        Marco marco = new Marco();
        String nombre = (String)memory.get(++programCounter);
        marco.setNombre(nombre);
        programCounter++;
        while(memory.get(programCounter) != null){ 
            if(memory.get(programCounter) instanceof String){
                if(((String)(memory.get(programCounter))).equals("Limite")){
                    Object parametro = stack.pop();
                    marco.agregarParametro(parametro);
                    programCounter++;
                }
            }
            else{ 
                ejecutarInstruccion(programCounter);
            }

        }
        marco.setRetorno(programCounter);
        stackMacros.add(marco);
        ejecutarFuncion((int)table.lookup(nombre));
    }
    
    private void push_parametro(){
        stack.push(stackMacros.lastElement().getParametro((int)memory.get(++programCounter)-1));
    }
    
    public void imprimirMemoria(){
        for(int i = 0; i < memory.size(); i++)
            System.out.println("" + i + ": " +memory.get(i));
    }
    
    public void ejecutar(){
        //imprimirMemoria();
        stop = false;
        while(programCounter < memory.size())
            ejecutarInstruccion(programCounter);
    }
    
    public boolean ejecutarSiguiente(){
        //imprimirMemoria();
        if(programCounter < memory.size()){
            ejecutarInstruccion(programCounter);
            return true;
        }
        return false;
    }
    
    public void ejecutar(int indice){  
        programCounter = indice;
        while(!stop && !returning){
            ejecutarInstruccion(programCounter);
        }
        stop = false;
    }
    
    public void ejecutarFuncion(int indice){
        programCounter = indice;
        while(!returning && memory.get(programCounter) != null){
            ejecutarInstruccion(programCounter);
        }
        returning = false;
        programCounter = stackMacros.lastElement().getRetorno();
        stackMacros.removeElement(stackMacros.lastElement());
    }
    
    public void ejecutarInstruccion(int indice){
        try{         
            Object objetoLeido = memory.get(indice);
            if(objetoLeido instanceof Method){
                Method metodo = (Method)objetoLeido;
                metodo.invoke(this, null);
            }
            if(objetoLeido instanceof Funcion){
                ArrayList parametros = new ArrayList();
                Funcion funcion = (Funcion)objetoLeido;
                programCounter++;
                while(memory.get(programCounter) != null){ 
                    if(memory.get(programCounter) instanceof String){
                        if(((String)(memory.get(programCounter))).equals("Limite")){
                            Object parametro = stack.pop();
                            parametros.add(parametro);
                            programCounter++;
                        }
                    }
                    else{ 
                        ejecutarInstruccion(programCounter);
                    }

                }
                funcion.ejecutar(currentState, parametros);
            }
            programCounter++;
        }
        catch(Exception e){}
    }
    
    public CurrentState getCurrentState(){
        return currentState;
    }
    
    public static class Girar implements Funcion{
        @Override
        public void ejecutar(Object A, ArrayList parametros) {
            CurrentState configuracion = (CurrentState)A;
            int angulo = (configuracion.getAngulo() + (int)(double)parametros.get(0))%360;
            configuracion.setAngulo(angulo);
        }
    }
    
    public static class Avanzar implements Funcion{
        @Override
        public void ejecutar(Object A, ArrayList parametros) {
            CurrentState configuracion = (CurrentState)A;
            int angulo = configuracion.getAngulo();
            double x0 = configuracion.getX();
            double y0 = configuracion.getY();
            double x1 = x0 + Math.cos(Math.toRadians(angulo))*(double)parametros.get(0);
            double y1 = y0 + Math.sin(Math.toRadians(angulo))*(double)parametros.get(0);
            configuracion.setPosicion(x1, y1);
            configuracion.agregarLinea(new Linea((int)x0,(int)y0,(int)x1,(int)y1, configuracion.getColor()));
        }
    }
    
    public static class CambiarColor implements Funcion{
        @Override
        public void ejecutar(Object A, ArrayList parametros) {
            CurrentState configuracion = (CurrentState)A;
            configuracion.setColor(new Color((int)(double)parametros.get(0)%256, (int)(double)parametros.get(1)%256, (int)(double)parametros.get(2)%256));
        }
    }
    
}
