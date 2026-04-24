# Program to calculate the hamming distance of 4 codewords

# Checks that number is correct length and is of binary form (0 and 1)
def validate(b):
    if len(b) != 8:
        raise ValueError("Length must be 8 bits!")
    if not all (c in '01' for c in b):
        raise ValueError("Number must only contain '0' or '1'.")

# Calculates the hamming distance between two 8 bit binary numbers 
def hammingDistance(a, b):
    distanceCount = 0
    for i in range(8):
        if a[i] != b[i]:
            distanceCount += 1
    return distanceCount

# Compares 4 codewords
def compareCodewords(codewords):
    smallestDist = 8
    for i in range(len(codewords) - 1):
        for j in range(i + 1, len(codewords)):
            currentDist = hammingDistance(codewords[i], codewords[j])
            if currentDist <= smallestDist:
                smallestDist = currentDist
    return smallestDist
    

# Main function that calls to validate and hammingDistance functions
def main():
    bString1 = input("Enter an 8 bit binary codeword.\n")
    bString2 = input("Enter another 8 bit binary number.\n")
    bString3 = input("Enter another 8 bit binary number.\n")
    bString4 = input("Enter another 8 bit binary number.\n")

    
    try:
        validate(bString1)
        validate(bString2)
        validate(bString3)
        validate(bString4)
        codewords = [bString1, bString2, bString3, bString4]
        print(compareCodewords(codewords))
    except ValueError as e:
        print(e)
        exit()

main()


"""
1. User enters 4 8 bit binary numbers.
2. Program checks that input is only 0s and 1s.
    - Error if not
3. Program checks input is length 8.
    - Error if not
4. Compute hamming distance between 6 codeword pairs.
5. Find the minimum distance of those pairs.
5. Output that minimum.

"""



