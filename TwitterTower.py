import math


def rectangle_tower(height, width):
    if height == width or abs(height - width) > 5:
        print("The area of the rectangle tower is", height * width)
    else:
        print("The scope of the rectangle tower is", height * 2 + width * 2)


def triangle_tower(height, width):
    triangle_action = int(input('===================================================\n'
                                'Please Enter your choice for triangle action :\n1-Triangle_scope\n2-Triangle_shape \n'
                                '===================================================\n'))
    if triangle_action == 1:
        # Pythagoras theorem for finding the third vertice of a triangle(hypotenuse)
        hypotenuse = math.sqrt(height ** 2 + (width / 2) ** 2)
        print("The scope of the rectangle tower is", hypotenuse * 2 + width)
    elif triangle_action == 2:
        if width % 2 == 0 or width / height > 2:
            print("It is not possible to print the triangle tower\n"
                  "if you want the triangle to be printed"
                  "\nchoose an odd width or the length will be less than 2 times the height...\n")
            return
        else:
            """
            number_of_odd_numbers= the number of odd numbers between 1-width
            midd_lines= the number of lines to be filled in the middle
            repeat_number= the number of lines of each num of asterisks
            remainder= the remainder of dividing the midd_lines by the number of odd numbers in the middle
            """
            number_of_odd_numbers = ((width - 2) // 2)
            midd_lines = (height - 2)
            # Also refers to an edge case number_of_odd_numbers<1, like in input width 3/1
            repeat_number = midd_lines // number_of_odd_numbers if number_of_odd_numbers > 0 else 0
            remainder = midd_lines % number_of_odd_numbers if number_of_odd_numbers > 0 else 0
            n = width // 2
            print(" " * n + "*" + " " * n)  # print the first line
            str_asterisk = " " * (n - 1) + "***" + " " * (n - 1) + "\n"
            print(str_asterisk * remainder, end="")  # print the remainder lines
            # continue reference to an edge case number_of_odd_numbers<1
            if width == 1:
                str_asterisk = " " * n + "*" * width + " " * n + "\n"
                print(str_asterisk * midd_lines)
            for i in range(1, number_of_odd_numbers + 1):  # each iterate print the lines of one of the odd number
                str_asterisk = " " * (n - i) + "*" * (2 * i + 1) + " " * (n - i) + "\n"
                print(str_asterisk * repeat_number, end="")
            print("*" * width)  # print the last line


if __name__ == '__main__':
    choice = 0
    while True:
        choice = int(input(
            '===================================================\n'
            'Please enter which type of shape you want for the tower: \n 1- Rectangle \n 2- Triangle \n 3-Exit \n'
            '===================================================\n'))
        if choice > 3:
            print('Error: Please select one of the 3 options')
            exit()
        if choice == 3:
            exit()
        height = int(input("Enter the tower's height\n"))
        width = int(input("Enter the tower's width\n"))
        if choice == 2:
            triangle_tower(height, width)
        if choice == 1:
            rectangle_tower(height, width)
