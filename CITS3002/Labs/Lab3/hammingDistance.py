# Program to calculate the hamming distance between two binary strings 

# Checks that number is correct length and is of binary form (0 and 1)
def validate(b):
    if len(b) != 8:
        raise ValueError("Length must be 8 bits!")
    if not all (c in '01' for c in b):
        raise ValueError("Number must only contain '0' or '1'.")
    
# Computes the hamming distance of the two 8 bit binary numbers    
def hammingDistance(a, b):
    distanceCount = 0
    for i in range(8):
        if a[i] != b[i]:
            distanceCount += 1
    return distanceCount

# Main function that calls to validate and hammingDistance functions
def main():
    bString1 = input("Enter an 8 bit binary number.\n")
    bString2 = input("Enter another 8 bit binary number.\n")

    try:
        validate(bString1)
        validate(bString2)
        print(hammingDistance(bString1, bString2))
    except ValueError as e:
        print(e)
        exit()

main()


"""
1. User enters two 8 bit binary numbers.
2. Program checks that input is only 0s and 1s.
    - Error if not
3. Program checks input is length 8.
    - Error if not
4. Compute hamming distance between two numbers.
5. Output the distance.

"""



