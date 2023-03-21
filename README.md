# compiler_ANTLER

## Starting rule

```antlr
compilationUnit
: packageDeclaration? importDeclaration* typeDeclaration*
| moduleDeclaration EOF
;
```

## Screenshot of parser tree for simple Java Program
![Parser Tree](https://github.com/ahmedokka29/compiler_ANTLER/blob/main/images/parseTree(with%20background).png)
