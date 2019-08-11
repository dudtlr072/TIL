N = int(input())
pre_a = 1
pre_b = 1
pre_c = 1

for i in range(1, N):
    temp_a = pre_a + pre_b + pre_c
    temp_b = pre_a + pre_c
    temp_c = pre_a + pre_b
    pre_a = temp_a
    pre_b = temp_b
    pre_c = temp_c

print((pre_a + pre_b + pre_c) % 9901)