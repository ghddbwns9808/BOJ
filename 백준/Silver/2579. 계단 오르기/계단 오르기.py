def solution(N):
    dp[2] = dp[1] + numbers[2]

    for i in range(3, N+1):
        dp[i] = max(dp[i-3] + numbers[i-1] + numbers[i], dp[i-2] + numbers[i])


N = int(input())
numbers = [0]
for _ in range(N):
    numbers.append(int(input()))

dp = [0 for _ in range(N+1)]
dp[1] = numbers[1]

if N == 1:
    print(dp[1])

else:
    solution(N)
    print(dp[N])
