# A program to generate Hamming Code for single bit error correction


def computeFullCodeword(codeword):
    hammedWord = [0] * 12
    dataIndex = 0
    for i in range(1, 13):
        if not (i & (i-1) == 0):
            hammedWord[i-1] = int(codeword[dataIndex])
            dataIndex += 1

    for p in [1, 2, 4, 8]:
        parityVal = 0
        for i in range(1, 13):
            if i & p != 0 and i != p:  # covered by p, but skip the parity position itself
                parityVal = parityVal ^ hammedWord[i-1]
        hammedWord[p-1] = parityVal
                
    
    return hammedWord


def validate(codeword):
    if len(codeword) != 8:
        raise ValueError("Length must be 8 bits!")
    if not all (c in '01' for c in codeword):
        raise ValueError("Number must only contain '0' or '1'.")

# Main function that calls on validation and codewordCalculation
def main():
    codeword = input("Please enter an 8 bit binary string\n")

    try:
        validate(codeword)
        print(computeFullCodeword(codeword))
    except ValueError as e:
        print(e)
        exit()


main()

"""
1. Take an 8 bit binary codeword as input
2. Verify that the codeword is binary (only 0 and 1)
    - ValueError
3. Verify that the codeword is 8 bits long.
    - ValueError
4. Compute full hamming codeword (original codeword + parity bits)
5. Output the full codeword.
"""