%{
  import java.io.*;
  import java.util.ArrayList;
%}
%token DIG
%token VAR
%token CNUMBER

%right '='
%left '-' '+'
%left '*' '/'

%%
list:	/* nada */
			| list '\n'
      | list asgn '\n' {}
			| list exp '\n' {Complejo res = (Complejo) $2.obj;imprimeComplejo(res);}
			;
asgn: VAR '=' exp     { Cadena c = (Cadena) $1.obj; 
                        Symbol s = lookUpTable(c.getCadena());
                        if (s == null)
                            install(c.getCadena(), (Complejo) $3.obj);
                          else
                            update(s, (Complejo) $3.obj);
                        }
      ;
exp:  CNUMBER         {Complejo c = (Complejo) $1.obj;}
      | VAR           { Cadena c = (Cadena) $1.obj;
                        Symbol s = lookUpTable(c.getCadena());
                        if (s != null) {
                          Complejo data = (Complejo) s.getData();
                          $$ = new ParserVal(data);
                        } else {
                          yyerror("Variable no declarada");
                          System.exit(0);
                        }
                      }
     | asgn
     | exp '+' exp {Complejo c1 = (Complejo) $1.obj; 
                    Complejo c2 = (Complejo) $3.obj;
                    Complejo res = sumaComplejos(c1, c2);
                    $$ = new ParserVal(res);
                   }
     | exp '-' exp {Complejo c1 = (Complejo) $1.obj; 
                    Complejo c2 = (Complejo) $3.obj;
                    Complejo res = restaComplejos(c1, c2);
                    $$ = new ParserVal(res);
                   }
     | exp '*' exp {Complejo c1 = (Complejo) $1.obj; 
                    Complejo c2 = (Complejo) $3.obj;
                    Complejo res = multiplicaComplejos(c1, c2);
                    $$ = new ParserVal(res);
                   }
     | exp '/' exp {Complejo c1 = (Complejo) $1.obj; 
                    Complejo c2 = (Complejo) $3.obj;
                    Complejo res = divideComplejos(c1, c2);
                    $$ = new ParserVal(res);
                   }
     | '(' exp ')' {Complejo c = (Complejo) $2.obj; $$ = new ParserVal(c); }
    ;
%%

  private Yylex lexer;


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


  public Complejo sumaComplejos(Complejo c1, Complejo c2) {
    Complejo res = new Complejo(c1.getReal() + c2.getReal(),
                        c1.getImg() + c2.getImg());
    return res;
  }
  public Complejo restaComplejos(Complejo c1, Complejo c2) {
    Complejo res = new Complejo(c1.getReal() - c2.getReal(),
                        c1.getImg() - c2.getImg());
    return res;
  }
  public Complejo multiplicaComplejos(Complejo c1, Complejo c2) {
    Complejo res = new Complejo((c1.getReal() * c2.getReal()) - (c1.getImg() * c2.getImg()),
                                  (c1.getImg() * c2.getReal()) + (c1.getReal() * c2.getImg()) );
    return res; 
  }
  public Complejo divideComplejos(Complejo c1, Complejo c2) {
    float d = (float) ( (c2.getReal() * c2.getReal()) + (c2.getImg() * c2.getImg()) );
    Complejo res = new Complejo((float) (c1.getReal() * c2.getReal() + c1.getImg() * c2.getImg()) / d,
                              (float) (c1.getImg() * c2.getReal() - c1.getReal() * c2.getImg()) / d );
    return res;
  }



/* Metodos de la tabla de símbolos */
ArrayList<Symbol> symbolTable = new ArrayList<>();

Symbol lookUpTable(String symbolName) {
  boolean flag = false;
  for (Symbol s: symbolTable) {
    if (s.getName().compareTo(symbolName) == 0) { // The symbol is already in the table !
      return s;
    }
  }

  return null;
}

void install(String name, Complejo data) {
  Symbol s = new Symbol(name, (short) 1, data);
  symbolTable.add(s);
}

void update(Symbol s, Complejo data) {
  int position = symbolTable.indexOf(s);
  Symbol sn = new Symbol(s.getName(), s.getType(), data);
  symbolTable.set(position,sn);
}

void imprimeComplejo(Complejo c) {
  System.out.println(c.getReal()+","+c.getImg()+"i");
}



public static void main(String args[]) throws IOException {
    System.out.println("Calculadora Números Complejos");

    Parser yyparser;

    System.out.println("Ingresa una expresion: ");

	  yyparser = new Parser(new InputStreamReader(System.in));

    yyparser.yyparse();

  }
