grammar DBSchema;

tabledef:
    'table' WS* NAME WS* '('
    ;

INT:
    'int'
   ;



NAME:
      ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')+
    ;

WS:
  (' ' | '\t')
  ;