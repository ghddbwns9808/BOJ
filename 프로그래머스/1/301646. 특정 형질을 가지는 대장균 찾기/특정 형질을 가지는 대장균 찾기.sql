-- 코드를 작성해주세요
SELECT COUNT(*) COUNT
FROM ECOLI_DATA
WHERE GENOTYPE & 5 > 0 AND GENOTYPE & 2 = 0;
