-- 코드를 작성해주세요

SELECT ID, FISH_NAME, MLI.LENGTH
FROM FISH_INFO FI JOIN FISH_NAME_INFO FNI
ON FI.FISH_TYPE = FNI.FISH_TYPE, 
(
    SELECT FISH_TYPE, MAX(LENGTH) AS LENGTH
    FROM FISH_INFO
    GROUP BY FISH_TYPE
) MLI
WHERE FI.FISH_TYPE = MLI.FISH_TYPE AND FI.LENGTH = MLI.LENGTH
ORDER BY ID
