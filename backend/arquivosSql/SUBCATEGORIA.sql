Select
	SUB.CD_ARV_MERC_CATEG_SUB AS CODIGO,
	SUB.DS_ARV_MERC_CATEG_SUB AS SUBCATEGORIA,
	CATEG.DS_ARV_MERC_CATEG AS CATEGORIA,
	DEP.DS_ARV_MERC_DEPTO AS DEPARTAMENTO
From
	EST_ARV_MERC_CATEGORIA_SUB SUB
	INNER JOIN EST_ARV_MERC_CATEGORIA CATEG
		ON SUB.CD_EMP = CATEG.CD_EMP
		AND SUB.CD_ARV_MERC_CATEG = CATEG.CD_ARV_MERC_CATEG
	INNER JOIN EST_ARV_MERC_DEPTO DEP
		ON CATEG.CD_EMP =  DEP.CD_EMP
		AND CATEG.CD_ARV_MERC_DEPTO =  DEP.CD_ARV_MERC_DEPTO
Where
	SUB.CD_EMP = 1