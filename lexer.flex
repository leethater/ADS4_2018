%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{

%}

blank = [\n\r \t]
number=[0-9]+
hex=[0-9A-F]
color=#{hex}{hex}{hex}{hex}{hex}{hex}
operator="+"|"-"|"/"|"*"
keyword=[A-Z][a-zA-Z]*

%%
"Begin" {
return new Token(Sym.BEGIN);
}
{keyword} {
return new WordToken(yytext());
}
{color}{
return new ColorToken(yytext());
}

{number} {
return new NumberToken(Integer.parseInt(yytext()));
}
{operator}{
return new OpToken(yytext());
}

"(" {
return new Token(Sym.LPAR);
}

")"{
return new Token(Sym.RPAR);
}

[^] {
}
