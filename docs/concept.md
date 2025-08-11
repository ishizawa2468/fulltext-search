# Concept

## やりたいこと

Spring Boot API サーバーを作り、その中で検索について以下のことを試したい。

- stage0: Spring Boot + JPA + postgresql で環境を構築する。検索対象は下にある csv データ。
  - output:
    - Makefile
    - docker-compose.yml
    - schema.sql
    - application-local.properties
- stage1: JPA の contains で単純な部分一致検索をする
  - output:
    - repository
    - entity
    - service
    - controller
- stage2: JPA Criteria API で AND/OR 検索を空白区切りのクエリに対して動的にできるようになる。
  - output:
    - refactored repository
    - refactored service
- stage3: ひらがな-カタカナの互換・半角-全角の互換・略称の対応のために、検索インデックステーブルを追加する

## 材料

### csv データの例

20250731,1301,極洋,プライム（内国株式）,50,水産・農林業,1,食品,7,TOPIX Small 2
20250731,1305,ｉＦｒｅｅＥＴＦ　ＴＯＰＩＸ（年１回決算型）,ETF・ETN,-,-,-,-,-,-
20250731,1306,ＮＥＸＴ　ＦＵＮＤＳ　ＴＯＰＩＸ連動型上場投信,ETF・ETN,-,-,-,-,-,-
20250731,1308,上場インデックスファンドＴＯＰＩＸ,ETF・ETN,-,-,-,-,-,-
20250731,1309,ＮＥＸＴ　ＦＵＮＤＳ　ＣｈｉｎａＡＭＣ・中国株式・上証５０連動型上場投信,ETF・ETN,-,-,-,-,-,-
20250731,130A,Ｖｅｒｉｔａｓ　Ｉｎ　Ｓｉｌｉｃｏ,グロース（内国株式）,3250,医薬品,5,医薬品,-,-
20250731,1311,ＮＥＸＴ　ＦＵＮＤＳ　ＴＯＰＩＸ　Ｃｏｒｅ　３０連動型上場投信,ETF・ETN,-,-,-,-,-,-
20250731,1319,ＮＥＸＴ　ＦＵＮＤＳ　日経３００株価指数連動型上場投信,ETF・ETN,-,-,-,-,-,-
20250731,131A,ＣＣＮグループ,PRO Market,5250,情報・通信業,10,情報通信・サービスその他,-,-
