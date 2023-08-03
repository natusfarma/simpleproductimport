INSERT INTO EST_PROD_FORN(
CD_EMP,
CD_PROD,
CD_FORN,
CD_PROD_FORN,
COMPRA_INATIVA,
COMPRA_OL,
APRES_EMB_CP,
APRES_QT_CP,
CD_USU,
DT_ULT_ALT,
FLAG_TIPO_CONVERSAO)
VALUES (
1,
codigoProduto,
codigoFornecedor,
'codigoProdutoFornecedor',
0,
0,
1,
20,
codigoUsuario,
CONVERT (date,GETDATE()),
0)