%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "regexp.h"

int yylex(void);
int yyerror(const char*);
struct elemento {
   NodoArb  *nodo;   
   char car;
};
typedef struct elemento Elemento;
#define YYSTYPE struct elemento
char meta[]={'|','*','(',')','[',']','$','.','-','^',':', 0};
int cta=0;
Conjunto *sig[1000];
%}
%token CHAR
//%token ANYNONMETACHAR METACHAR

%left ':'
%left '|'
%left '*'
%%
input: /* vacio */ 
   | input line
   ;
line: '\n'
   | {  
        int i;
        cta = 0 ; 
        for(i=0; i < 1000 ;i++){
           sig[i]=creaConjunto(10);
        }
     } 
     re '\n' { 
          Conjunto *c1, *c2;
          cta=cta+1;
          NodoArb *raiz;
          NodoArb *gato=creaNodoArb(creaNodoI(strdup("#"), cta, 0), NULL, NULL);
          c1=creaConjunto(100);   
          ((NodoI *)gato->info)->last=insertar(c1, 
                      creaNodoI(strdup("#"), cta, 0), cmpNodoNum, copiaNodoI);
          c2=creaConjunto(100);
          ((NodoI *)gato->info)->first=insertar(c2, 
                      creaNodoI(strdup("#"), cta, 0), cmpNodoNum, copiaNodoI);
          raiz= creaNodoArb(creaNodoI(strdup(":"), 0, 0), $2.nodo, gato);
          if(((NodoI *)$2.nodo->info)->anu)
             ((NodoI *)raiz->info)->first=
                    insertar(firstPos($2.nodo), 
                     creaNodoI(strdup("#"), cta, 0), cmpNodoNum , copiaNodoI);
          else
             //((NodoI *)raiz->info)->first=((NodoI *)$2.nodo->info)->first;
             ((NodoI *)raiz->info)->first=firstPos($2.nodo);
          ((NodoI *)raiz->info)->last=((NodoI *)gato->info)->last;

          imprimeArb(raiz, imprimeNodoII); puts("*****SIGSIGSIG "); 
          followPos(raiz); impriSig(); 
          crearEstados(raiz);
     }
   ;
re: re ':' re {
          $$.nodo = creaNodoArb(creaNodoI(strdup(":"), cta,
             ((NodoI *)$1.nodo->info)->anu && ((NodoI *)$3.nodo->info)->anu),
                    $1.nodo, $3.nodo);
   }
   | re '|' re {   
          $$.nodo = creaNodoArb(creaNodoI(strdup("|"), cta,
              ((NodoI *)$1.nodo->info)->anu || ((NodoI *)$3.nodo->info)->anu),
                    $1.nodo, $3.nodo);
   }
   | '*' re {
          $$.nodo = creaNodoArb(creaNodoI(strdup("*"), cta, 1 ),
                                $2.nodo, NULL);
   }
   | '(' re ')' { $$ = $2; }
   | CHAR {
       char cade[2]={0,0};
       cade[0]=$1.car;
       cta=cta+1;
       $$.nodo = creaNodoArb(creaNodoI(strdup(cade), cta, 0),
                          (NodoArb *)NULL,(NodoArb *)NULL); 
   }
   ;
%%
void main() {  yyparse();  }
int yyerror(const char* s) { 
   printf("%s\n", s); 
   return 0; 
}
int busca(char c){
   int i;
   for (i = 0; meta[i] ; i++)
      if (meta[i] == c) return 1;
   return 0;
}
int yylex (){
   int c;
   while ((c = getchar ()) == ' ' || c == '\t')  
  		;
   if (c == EOF) return 0;
   if(c != '\n' &&  busca(c)==0 ){
      yylval.car=c;
      return CHAR;
   }
   return c;                                
}
NodoI *creaNodoI(char *dato, int num, int anu){
   NodoI *nvo;
   nvo=(NodoI *)malloc(sizeof(NodoI));
   if(!nvo){
      puts("no hay memoria para crear NodoArb");
      return (NodoI *)NULL;
   }
   nvo->num=num;
   nvo->anu=anu;
   nvo->id=dato;
   return nvo;
}
void *copiaNodoI(void *p){
   NodoI *nvo=(NodoI *)p;
   return creaNodoI(nvo->id, nvo->num, nvo->anu);
}
void impriSig(){
   int i;
   for(i=0; i < cta+1 ;i++){
      printf("SIGSIG__ %d  ", i);
      imprimeConjunto(sig[i], imprimeNodoI);
   }
}
int contiene(NodoL *head,  NodoL *tail, Conjunto *ele){
   NodoL *p; 
   puts("1contiene");
   imprimeConjunto( (Conjunto *)head->info, imprimeNodoI);
   puts("2contiene");
   for(p=head; p!=tail; p=p->sig){
      puts("3contiene");
      imprimeConjunto( (Conjunto *)p->info, imprimeNodoI);
      if(iguales((Conjunto *)p->info, ele, cmpNodoNum) ) return 1; 
   }
   if(iguales((Conjunto *)p->info, ele, cmpNodoNum) ) return 1;
   return 0;
}
int cmpNodoNum(void *p1, void *p2){
   NodoI *ni1=(NodoI*)p1;
   NodoI *ni2=(NodoI*)p2;
   return ni1->num - ni2->num;
}
int cmpNodoID(void *p1, void *p2){
   NodoI *ni1=(NodoI*)p1;
   NodoI *ni2=(NodoI*)p2;
   return strcmp(ni1->id, ni2->id);
}
void imprimeNodoI(void *v){
   NodoI *ni=(NodoI *)v;
   puts("imprimeI");
   printf("%s %d ",(char *)ni->id, ni->num);
}
void imprimeNodoII(void *v){
   NodoI *ni=(NodoI *)v;
   puts("imprimeII");
   printf("%s %d %d",(char *)ni->id, ni->num, ni->anu);
}
void imprimeCade(void *s){
   printf(" %s ",(char *)s);
}



