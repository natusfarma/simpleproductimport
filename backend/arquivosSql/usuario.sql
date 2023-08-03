SELECT
	CD_FUN as codigo,
	NM_FUN as nome
FROM
	FP_FUNC
WHERE
	STS_FUN = 0
	AND CD_FUN = codigoUsuario