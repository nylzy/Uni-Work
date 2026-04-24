# Program to determine if a 12 bit Hammed Codeword contains an error

# Checks that number is correct length and is of binary form (0 and 1)
def validate(codeWord):
    if len(codeWord) != 12:
        raise ValueError("Length must be 12 bits!")
    if not all (c in '01' for c in codeWord):
        raise ValueError("Number must only contain '0' or '1'.")
    
# Computes a full 12 bit codeword from 8 bit codeword
def computeFullCodeword(codeWord):
    hammedWord = [0] * 12
    dataIndex = 0
    for i in range(1, 13):
        if not (i & (i-1) == 0):
            hammedWord[i-1] = int(codeWord[dataIndex])
            dataIndex += 1

    for p in [1, 2, 4, 8]:
        parityVal = 0
        for i in range(1, 13):
            if i & p != 0 and i != p:  # covered by p, but skip the parity position itself
                parityVal = parityVal ^ hammedWord[i-1]
        hammedWord[p-1] = parityVal
                
    
    return hammedWord

# Extracts the data bits from the 12 bit codeword
def extract_data_bits(codeWord):
    parity_positions = {1, 2, 4, 8}
    data_bits = ""
    for i in range(1, 13):
        if i not in parity_positions:
            data_bits += codeWord[i - 1]
    return data_bits
            

# Main function that calls to validate and hammingDistance functions
def main():
    bString1 = input("Enter a 12 bit binary codeword.\n")

    try:
        validate(bString1)
        recomputed = ''.join(str(b) for b in computeFullCodeword(extract_data_bits(bString1)))
        if recomputed != bString1:
            print("The codeword has an error!")
        else:
            print("No error detected.")
    except ValueError as e:
        print(e)
        exit()
main()


"""
1. User enters a 12 bit binary codeword.
2. Program checks that input is only 0s and 1s.
    - Error if not
3. Program checks input is length 12.
    - Error if not
4. Establishes parity positions.
5. Stores which values those parity positions track.
6. XOR's those values to obtain either 0 or 1.
5. Compare that to parity values
6. Output error if the comparison didn't match

"""
