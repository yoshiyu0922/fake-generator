# fake-generator

# Environment

- Scala2.12
  - javafaker
  - scala-csv

# Class: faker.Person

- ランダムにpersonとaddressのデータを作成してcsvを出力
- 実行引数に出力するデータ件数を指定できる

```sbtshell
# 100件出力する例
sbt "runMain faker.Person 100" 
```

