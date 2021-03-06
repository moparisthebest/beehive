/* Generated By:JavaCC: Do not edit this line. SqlGrammarConstants.java */
package org.apache.beehive.controls.system.jdbc.parser;

public interface SqlGrammarConstants {

  int EOF = 0;
  int NON_EXPRESSION_TEXT = 1;
  int START_EXPRESSION = 2;
  int SQUOTE = 3;
  int STRING_LITERAL = 4;
  int ECMA_ESCAPE_SEQUENCE = 5;
  int HIT = 6;
  int END_EXPRESSION = 7;
  int REFLECT_SEP = 8;
  int SQL_ESCAPE = 9;
  int SQL_SUBST = 10;
  int SQL_FN = 11;
  int JDBC_CALL = 12;
  int JDBC_RET = 13;
  int JDBC_DATE = 14;
  int JDBC_TIME = 15;
  int JDBC_TIMESTAMP = 16;
  int JDBC_FUNCTION = 17;
  int JDBC_ESCAPE = 18;
  int JDBC_OUTERJOIN = 19;
  int WHITESPACE = 20;
  int IDENTIFIER = 21;
  int LETTER = 22;
  int DIGIT = 23;
  int SQL_FN_END = 24;
  int SQL_FN_NM = 25;
  int SQL_FN_PAREN = 26;
  int SQL_FN_COMMA = 27;
  int SQL_FN_PSTART = 28;
  int SQL_FN_PEND = 29;
  int SQL_FN_WHITESPACE = 30;
  int SQL_FN_IDENTIFIER = 31;
  int JDBC_END = 32;
  int JDBC_LIT = 33;
  int JDBC_PARAM = 34;
  int PARAM_IDENTIFIER = 35;
  int PARAM_REFLECT_SEP = 36;
  int PARAM_LITERAL = 37;
  int PARAM_END = 38;

  int DEFAULT = 0;
  int IN_LITERAL = 1;
  int IN_EXPRESSION = 2;
  int IN_SQLFN = 3;
  int IN_JDBC = 4;
  int IN_PARAM = 5;

  String[] tokenImage = {
    "<EOF>",
    "<NON_EXPRESSION_TEXT>",
    "\"{\"",
    "\"\\\'\"",
    "<STRING_LITERAL>",
    "<ECMA_ESCAPE_SEQUENCE>",
    "<HIT>",
    "\"}\"",
    "\"|\"",
    "<SQL_ESCAPE>",
    "<SQL_SUBST>",
    "<SQL_FN>",
    "<JDBC_CALL>",
    "<JDBC_RET>",
    "<JDBC_DATE>",
    "<JDBC_TIME>",
    "<JDBC_TIMESTAMP>",
    "<JDBC_FUNCTION>",
    "<JDBC_ESCAPE>",
    "<JDBC_OUTERJOIN>",
    "<WHITESPACE>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "\")\"",
    "\"in\"",
    "\"(\"",
    "\",\"",
    "<SQL_FN_PSTART>",
    "<SQL_FN_PEND>",
    "<SQL_FN_WHITESPACE>",
    "<SQL_FN_IDENTIFIER>",
    "<JDBC_END>",
    "<JDBC_LIT>",
    "<JDBC_PARAM>",
    "<PARAM_IDENTIFIER>",
    "<PARAM_REFLECT_SEP>",
    "<PARAM_LITERAL>",
    "<PARAM_END>",
  };

}
