stack = []

def put(value):
    stack.append(value)

def pop():
    return stack.pop()

put(1)
print(stack)
put(2)
print(stack)
pop()
print(stack)