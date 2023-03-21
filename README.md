# compiler_ANTLER

## Starting rule

```antlr
compilationUnit
: packageDeclaration? importDeclaration* typeDeclaration*
| moduleDeclaration EOF
;
```

-----------

The Test code used to generate the parser tree

```java
public class Input {
    public static void main(String[] args) {
        if( x == true ) {
            if (z  == false){
                print();
            }
        }
    }
}
```


## Screenshot of parser tree for simple Java Program
![Parser Tree](https://github.com/ahmedokka29/compiler_ANTLER/blob/main/images/parseTree(with%20background).png)
