%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%yylexthrow LexerException

%{
  class LexerException extends Exception{
    public LexerException(int line, int column, String c){
      super("Line :"+line+" Column : "+column+" Character : "+c);
    }
  }

  public String getPosition(){
       return "Reading at line "+yyline+", column "+yycolumn;
    }
%}


blank=" "|"\n"|"\t"|"\r"
number=[0-9]+
hex=[0-9A-F]
color=#{hex}{hex}{hex}{hex}{hex}{hex}
operator="+"|"-"|"/"|"*"
ident = [a-z][a-zA-Z_]*

%%
"Begin" {return new Token(Sym.BEGIN);}
"End" {return new Token(Sym.END);}
"If" {return new Token(Sym.IF);}
"Then" {return new Token(Sym.THEN);}
"Else" {return new Token(Sym.ELSE);}
"DrawCircle" {return new Token(Sym.DRAWC);}
"FillCircle" {return new Token(Sym.FILLC);}
"DrawRect" {return new Token(Sym.DRAWR);}
"FillRect" {return new Token(Sym.FILLR);}
"Const" {return new Token(Sym.CONST);}

{color} {return new ColorToken(yytext());}
{number} {return new NumberToken(Integer.parseInt(yytext()));}
{ident} {return new WordToken(Sym.IDENT, yytext());}

"+" {return new Token(Sym.PLUS);}
"-" {return new Token(Sym.MINUS);}
"/" {return new Token(Sym.DIV);}
"*" {return new Token(Sym.MULT);}
"(" {return new Token(Sym.LPAR);}
")" {return new Token(Sym.RPAR);}
"," {return new Token(Sym.COMMA);}
";" {return new Token(Sym.SEMIC);}
"=" {return new Token(SYM.EQUALS);}
{blank} {}
[^] {throw new LexerException(yyline, yycolumn,yytext());}
