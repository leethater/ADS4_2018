

%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{
   public String getPosition(){
      return "Reading at line "+yyline+", column "+yycolumn;
   }

%}



%yylexthrow Exception

blank = [\n\r \t] 
string = \"[^\"]*\"
mot = [a-zA-Z]+

%%
"<?"[^]*"?>"	 {return new Token(Sym.PROLOGUE);}

 //completer ici:
 "<"		{return new Token(Sym.LCHEVRON);}
 ">"		{return new Token(Sym.RCHEVRON);}
 "/"		{return new Token(Sym.SLASH);}
 "="		{return new Token(Sym.EQ);}
{string}	{return new ValuedToken(Sym.STRING,yytext().substring(1,yylength()-1));}
{mot}		{return new ValuedToken(Sym.MOT,yytext());}
{blank}		{}
 
 [^]		{throw new Exception("Lexer error at line "+ yyline + " column " + yycolumn);}
 <<EOF>>    	{return new Token(Sym.EOF);}  /*this is executed when the end of file is reached*/



