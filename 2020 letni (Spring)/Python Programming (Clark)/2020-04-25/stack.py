# 1. Create a stack using Python's list
def makeStack():
  return []

def stackEmpty(stack):
  return not stack

def stackPush(stack, e):
  stack.append(e)

def stackPeek(stack, default = None):
  if not default:
    return stack[-1]
  else:
    if stackEmpty(stack):
      return default
    else:
      return stack[-1]

def stackPop(stack, default = None):
  if not default:
    return stack.pop()
  else:
    if stackEmpty(stack):
      return default
    else:
      return stack.pop()
