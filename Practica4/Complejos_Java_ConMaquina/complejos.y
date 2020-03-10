%{
  import java.io.*;  
%}

%token BLTIN
%token DIG
%token VAR
%token CNUMBER

%right '='
%left '-' '+'
%left '*' '/'

  /* RULES SECTION */
%%
list:	/* nada */
			| list '\n'
      | list asgn '\n'  { /*maq.code("printVar"); */maq.code("STOP"); 
                          maq.execute(flag); flag = true; 
                        }
			| list exp '\n'   {  maq.code("printComplex"); maq.code("STOP"); 
                            maq.execute(flag); flag = true;
                        }
			;
asgn: VAR '=' exp   {  Cadena c = (Cadena)$1.obj;
                      maq.code("varPush");
                      maq.code(c);
                      maq.code("asgVar");       
                    }
      ;
exp:  CNUMBER                     { Complejo c = (Complejo) $1.obj; 
                                    maq.code("cNumber");
                                    maq.code(c);
                                  }
     | VAR                        { Cadena c = (Cadena)$1.obj;
                                    maq.code("varPush");
                                    maq.code(c);
                                    maq.code("getVarValue");
                                  }
     | asgn     
     | BLTIN '(' exp ')'          { Cadena c = (Cadena)$1.obj;
                                    maq.code("bltinPush");
                                    maq.code(c);
                                    maq.code("bltin");                                    
                                  }
     | exp '+' exp                { maq.code("add"); }  
     | exp '-' exp                { maq.code("sub"); }
     | exp '*' exp                { maq.code("mult"); }
     | exp '/' exp                { maq.code("div"); }
     | exp '^' DIG                { maq.code("powN");
                                    maq.code((Cadena) $3.obj);
                                    maq.code("pow");                                    
                                  }
     | '(' exp ')'                { Complejo c = (Complejo) $2.obj; $$ = new ParserVal(c); }
    ;
%%

/** CÓDIGO DE SOPORTE **/
  static Maquina maq = new Maquina();
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
  
  boolean flag = false;
  
  public static void main(String args[]) throws IOException {    
    System.out.println(".:: Complex Number Calculator ::.");

    Parser yyparser;
    
    System.out.println("Expression:");
    
	  yyparser = new Parser(new InputStreamReader(System.in));    

    maq.initCode();

    yyparser.yyparse();

  }
