        a=1
	b=1
        cta=0
	while(cta < 10){
           f=a+b
	   a=b
           b=f
	   print f
           cta= cta+1
	}


func suma(){
	return $1+$2
}
x=suma(4,6)
proc fibo(){
	a=1
	b=1
        cta=0
	while(cta < $1){
           f=a+b
	   a=b
           b=f
	   print f
           cta= cta+1
	}
}

func fac(){
	if($1==0) return 1 else return $1*fac($1-1)
}
x=fac(7)
proc bina(){
	cociente=int($1/2)
	while(cociente >= 1){
		print cociente
		cociente=int(cociente/2)
	}
}
func pascal(){
  if( $2 == 0 || $2 == $1 ) return 1 else return pascal($1-1, $2-1)+pascal($1-1, $2)
}
proc triang(){
   i= 0
   while(i < $1 + 1){
     print pascal($1, i)
     i=i+1
   }  
}
triang( 3 )

func pascal(m, n){
  if( n == 0 || n == m ) return 1 else return pascal(m-1, n-1)+pascal(m-1, n)
}
proc triang( m ){
   i= 0
   while(i < m + 1){
     print pascal(m, i)
     i=i+1
   }  
}
triang( 3 )

pascal( 1, 1)
print "Hola todos"
print "dame un numero"
read (x)
5
print x
fac(x)
fibo(x+3)
bina(61)

struct punto { a
b
c
d
e
f
g
h
i
j
k
l
}

struct punto p

proc test(){
    p.a = 0
    p.b = 1
    p.c = 2
    p.d = 3
    p.e = 4
    p.f = 5
    p.g = 6
    p.h = 7
    p.i = 8
    p.j = 9
    p.k = 10
    p.l = 11
    print p.a, p.b, p.c, p.d, p.e, p.f, p.g, p.h, p.i, p.j, p.k, p.l
}
test()
