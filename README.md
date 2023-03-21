# compiler_ANTLER

## Starting rule

```antlr
compilationUnit
: packageDeclaration? importDeclaration* typeDeclaration*
| moduleDeclaration EOF
;
```
