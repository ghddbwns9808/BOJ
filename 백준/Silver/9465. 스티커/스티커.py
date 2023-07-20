def dp(N):
    for i in range(2, N):
        cache[0][i] = max(cache[1][i-1], cache[1][i-2]) + sticker[0][i]
        cache[1][i] = max(cache[0][i-1], cache[0][i-2]) + sticker[1][i]


tc= int(input())

for c in range(tc):
    n = int(input())
    sticker = [list(map(int, input().split())), list(map(int, input().split()))]
    if n == 1:
        print(max(sticker[0][0], sticker[1][0]))
        continue

    cache = [[0 for _ in range(100000)] for _ in range(2)]
    cache[0][0] = sticker[0][0]
    cache[1][0] = sticker[1][0]
    cache[0][1] = sticker[1][0] + sticker[0][1]
    cache[1][1] = sticker[0][0] + sticker[1][1]

    dp(n)
    print(max(cache[0][n-1], cache[1][n-1]))