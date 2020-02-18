%{
  import java.io.*;
  import java.util.ArrayList;
%}

%token VAR
%token CNUMBER
%token BLTIN
%token DIG

%right '='
%left '-' '+'
%left '*' '/'

%%
list:	/* nada */
			| list '\n'
      | list asgn '\n' {}
			| list exp '\n' {Complejo res = (Complejo) $2.obj; res.imprimeComplejo();}
			;
asgn: VAR '=' exp   { Cadena c = (Cadena) $1.obj;
                      Symbol s = lookUpTable(c.getCadena());
                      if (s == null)
                        install(c.getCadena(), (Complejo) $3.obj, (short) 1);
                      else
                        update(s, (Complejo) $3.obj);
                    }
      ;
exp:  CNUMBER       {Complejo c = (Complejo) $1.obj;}
      | VAR         { Cadena c      = (Cadena) $1.obj;
                      Symbol s      = lookUpTable(c.getCadena());
                      if (s != null) {
                        Complejo data = (Complejo) s.getData();
                        $$ = new ParserVal(data);
                      } else {
                        yyerror("Variable no declarada");
                        System.exit(0);
                      }
                    }
     | asgn
     | BLTIN '(' exp ',' DIG ')'  { Cadena c1       = (Cadena) $1.obj;
                                    Symbol s        = lookUpTable(c1.getCadena());
                                    String fName    = s.getName();
                                    Complejo res    = new Complejo(0, 0);
                                    Cadena powN     = (Cadena) $5.obj;

                                    if (fName.compareTo("pow") == 0) {
                                      res = f.pow((Complejo) $3.obj, powN);
                                    }
                                    $$ = new ParserVal(res);

                                  }
     | BLTIN '(' exp ')'          { Cadena c        = (Cadena) $1.obj;
                                    Symbol s        = lookUpTable(c.getCadena());
                                    String fName    = s.getName();
                                    Complejo res    = new Complejo(0, 0);

                                    if (fName.compareTo("exp") == 0) {
                                      res = f.exp((Complejo) $3.obj);
                                    } else if (fName.compareTo("sin") == 0) {
                                      res = f.sinus((Complejo) $3.obj);
                                    } else if (fName.compareTo("cos") == 0) {
                                      res = f.cosine((Complejo) $3.obj);
                                    }
                                    $$ = new ParserVal(res);
                                  }
     | exp '+' exp                { Complejo c1   = (Complejo) $1.obj; Complejo c2 = (Complejo) $3.obj;
                                    Complejo res  = new Complejo(0,0);
                                    res.sumaComplejos(c1, c2);
                                    $$            = new ParserVal(res);
                                  }
     | exp '-' exp                { Complejo c1   = (Complejo) $1.obj; Complejo c2 = (Complejo) $3.obj;
                                    Complejo res  = new Complejo(0,0);
                                    res.restaComplejos(c1, c2);
                                    $$            = new ParserVal(res);
                                  }
     | exp '*' exp                { Complejo c1   = (Complejo) $1.obj; Complejo c2 = (Complejo) $3.obj;
                                    Complejo res  = new Complejo(0,0);
                                    res.multiplicaComplejos(c1, c2);
                                    $$            = new ParserVal(res);
                                  }
     | exp '/' exp                { Complejo c1   = (Complejo) $1.obj; Complejo c2 = (Complejo) $3.obj;
                                    Complejo res  = new Complejo(0,0);
                                    res.divideComplejos(c1, c2);
                                    $$            = new ParserVal(res);
                                  }
     | '(' exp ')'                {Complejo c = (Complejo) $2.obj; $$ = new ParserVal(c); }
    ;
%%

/** CÓDIGO DE SOPORTE **/
  private Yylex lexer;
  Functions f     = new Functions();

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }

/* Metodos de la tabla de símbolos */
ArrayList<Symbol> symbolTable = new ArrayList<>();

Symbol lookUpTable(String symbolName) {
  for (Symbol s: symbolTable) {
    if (s.getName().compareTo(symbolName) == 0) { // The symbol is already in the table !
      return s;
    }
  }
  return null;
}

void install(String name, Complejo data, short type) {
  Symbol s = new Symbol(name, type, data);
  symbolTable.add(s);
}

void update(Symbol s, Complejo data) {
  Symbol newSymbol = s;
  symbolTable.remove(s);
  newSymbol.setData(data);
  symbolTable.add(newSymbol);
}

/* Método para la inicializacion de las funciones disponibles */
void init() {
  /* Se inicializan las funciones */
  String[] funcNames = {"exp", "sin", "cos", "pow"};
  for (int i = 0; i < funcNames.length; i++) {
    install(funcNames[i], null, (short) 2);
  }
}

  public static void main(String args[]) throws IOException {
    System.out.println("Calculadora Números Complejos Cientifica");

    Parser yyparser;

    System.out.print("Ingresa una expresion: ");

	  yyparser = new Parser(new InputStreamReader(System.in));

    yyparser.init();

    yyparser.yyparse();

  }
