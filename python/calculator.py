# calculator with all buttons


import simplegui

# intialize globals
stack1 = 0
stack2 = 0
operator = ""
width = 300
height = 300
calc_start = "Not Started"
temp_num = 0

# event handlers for calculator with a store and operand

def output():
    """prints contents of store and operand"""
    print "Stack 2 = ", stack2
    print "Stack 1 = ", stack1
    print ""
    
def swap():
    """ swap contents of store and operand"""
    global stack1, stack2
    stack1, stack2 = stack2, stack1
    output()

def state():
    global calc_start, stack1, stack2, temp_num
    if calc_start == "Started":
        temp_num = float(input.get_text())
    elif calc_start == "Not Started":
        stack1 = float(input.get_text())
        calc_start = "Started"
    
def add():
    """ add operand to store"""
    global stack1, stack2, temp_num, calc_start
    state()
    if calc_start == "Started":
        stack1 = stack1 + temp_num
    elif calc_start == "RPN":
        stack1 = stack2 + stack1
        stack2 = 0
        calc_start = "Started"
    output()

def sub():
    """ subtract operand from store"""
    "" Put your code here ""
    output()

def mult():
    """ multiply store by operand"""
    "" Put your code here ""
    output()

def div():
    "" Put your code here ""
    output()

def enter(t):
    """ enter a new operand"""
    global stack1, calc_start, stack2
    if calc_start == "Not Started":
        stack1 = float(input.get_text())
        calc_start = "Started"
    elif calc_start == "Started":
        stack2 = stack1
        stack1 = float(input.get_text())
        calc_start = "RPN"
    output()

def clear():
    """ clears results """
    global stack1, stack2, calc_start
    stack1, stack2 = 0, 0
    calc_start = "Not Started"
    output()

def draw(canvas):
    global stack1, stack2, operator, width, height
    #expression = store + " " + operator + " " + operand + " = "
    pad = 12
    button_size = [60,60]
    x_start = (width - (3*button_size[0]+2*pad))/2
    for j in range(3):
        for k in range(3):
            A = [(x_start + k*(button_size[0]+pad)), ((j+1)*pad + j*button_size[1])]
            B = [(x_start + (k+1)*button_size[1] + k*pad), ((j+1)*pad + j*button_size[1])]
            C = [(x_start + (k+1)*button_size[1] + k*pad), ((j+1)*(pad + button_size[1]))]
            D = [(x_start + k*(button_size[0]+pad)), ((j+1)*(pad + button_size[1]))]
            canvas.draw_polyline([A, B, C, D, A], 5, "White")
            
# create frame
f = simplegui.create_frame("Calculator",width,height)

# register event handlers and create control elements
f.add_button("Print", output, 100)
f.add_button("Swap", swap, 100)
f.add_button("Add", add, 100)
f.add_button("Sub", sub, 100)
f.add_button("Mult", mult, 100)
f.add_button("Div", div, 100)
f.add_button("Clear", clear, 100)
input = f.add_input("Type a number and press 'Enter'", enter, 100)

# get frame rolling
f.start()
f.set_draw_handler(draw)