def binarySearch(N):
    start = 1 
    end = lans[len(lans) - 1]

    while start <= end:
        mid = int((start + end) / 2)
        if canMake(mid, N):
            start = mid + 1
        else:
            end = mid - 1

    return start

def canMake(lenght, N):
    cnt = 0
    for lan in lans:
        cnt += int(lan / lenght)

    if N <= cnt:
        return True
    else:
        return False



K, N = map(int, input().split())

lans = []
for _ in range(K):
    lans.append(int(input()))
lans = sorted(lans)
print(binarySearch(N) - 1)