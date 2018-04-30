class Exceptions{

}

class AssignmentException extends Exception{
  public AssignmentException (String s){
    super(s);
  }
}

class DeclarationException  extends Exception{
  public DeclarationException (String s){
    super(s);
  }
}

class ParserException extends Exception{
  public ParserException(String s){
    super(s);
  }
}
