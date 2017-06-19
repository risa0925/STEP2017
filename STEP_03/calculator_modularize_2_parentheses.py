def readNumber(line, index):
    number = 0.0
    while index < len(line) and line[index].isdigit():
        number = number * 10 + int(line[index])
        index += 1
    if index < len(line) and line[index] == '.':
        index += 1
        keta = 0.1
        while index < len(line) and line[index].isdigit():
            number += int(line[index]) * keta
            keta *= 0.1
            index += 1
    token = {'type': 'NUMBER', 'number': number}
    return token, index


def readPlus(line, index):
    token = {'type': 'PLUS'}
    return token, index + 1

def readMinus(line, index):
    token = {'type': 'MINUS'}
    return token, index + 1

def readMultiply(line, index):
    token = {'type': 'MULTIPLY'}
    return token, index + 1

def readDivide(line, index):
    token = {'type': 'DIVIDE'}
    return token, index + 1

def readParenL(line, index):
    token = {'type': 'PAREN_L'}
    return token, index + 1

def readParenR(line, index):
    token = {'type': 'PAREN_R'}
    return token, index + 1

def tokenize(line):
    tokens = []
    index = 0
    while index < len(line):
        if line[index].isdigit():
            (token, index) = readNumber(line, index)
        elif line[index] == '+':
            (token, index) = readPlus(line, index)
        elif line[index] == '-':
            (token, index) = readMinus(line, index)
        elif line[index] == '*':
            (token, index) = readMultiply(line, index)
        elif line[index] == '/':
            (token, index) = readDivide(line, index)
        elif line[index] == '(':
            (token, index) = readParenL(line, index)
        elif line[index] == ')':
            (token, index) = readParenR(line, index)
        else:
            print 'Invalid character found: ' + line[index]
            exit(1)
        tokens.append(token)
    return tokens

def evaluateFirst(tokens):
   index = 1
   while index < len(tokens):
        if tokens[index]['type'] in {'MULTIPLY', 'DIVIDE'}:
            calculated = 0.0
            if tokens[index]['type'] == 'MULTIPLY':
                calculated = tokens[index - 1]['number'] * tokens[index + 1]['number']

            elif tokens[index]['type'] == 'DIVIDE':
                try:
                    calculated = tokens[index - 1]['number'] / tokens[index + 1]['number']
                except Exception as e:
                    print('%r' % e)
            tokens[index - 1:index + 2] = [{'type': 'NUMBER', 'number': calculated}]
            index -= 1
        index += 1

def evaluate(tokens):
    answer = 0
    tokens.insert(0, {'type': 'PLUS'}) # Insert a dummy '+' token
'''
    while index < len(tokens):
        if tokens[index]['type'] in {'PAREN_L', 'PAREN_R'}:
            if tokens[index]['type'] == 'PAREN_L':
'''
    evaluateFirst(tokens)
    index = 1
    while index < len(tokens):
        if tokens[index]['type'] == 'NUMBER':
            if tokens[index - 1]['type'] == 'PLUS':
                answer += tokens[index]['number']
            elif tokens[index - 1]['type'] == 'MINUS':
                answer -= tokens[index]['number']
            else:
                print 'Invalid syntax'
        index += 1
    return answer

def test(line, expectedAnswer):
    tokens = tokenize(line)
    actualAnswer = evaluate(tokens)
    if abs(actualAnswer - expectedAnswer) < 1e-8:
        print "PASS! (%s = %f)" % (line, expectedAnswer)
    else:
        print "FAIL! (%s should be %f but was %f)" % (line, expectedAnswer, actualAnswer)


# Add more tests to this function :)
def runTest():
    print "==== Test started! ===="
    test("1+2", 3)
    test("1.0+2.1-3", 0.1)
    test("1+2+3*5/5", 6)
    test("2*5/5", 2)
    test("2.0*5/5.0", 2)
    test("2/0", 0)
    print "==== Test finished! ====\n"

runTest()

while True:
    print '> ',
    line = raw_input()
    tokens = tokenize(line)
    answer = evaluate(tokens)
    print "answer = %f\n" % answer