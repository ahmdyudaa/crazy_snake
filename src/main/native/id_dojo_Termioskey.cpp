#include <iostream>
#include <termios.h>
#include <unistd.h>


void detectArrowKeys() {

    char c;
    while (true) {
        ssize_t n = read(STDIN_FILENO, &c, 1);
        if (n > 0) {
            if (c == '\033') { // Escape character
                char seq[2];
                if (read(STDIN_FILENO, &seq[0], 1) == 0) continue;
                if (read(STDIN_FILENO, &seq[1], 1) == 0) continue;

                if (seq[0] == '[') {
                    switch (seq[1]) {
                        case 'A':
                            std::cout << "Up arrow key pressed\n";

                            break;
                        case 'B':
                            std::cout << "Down arrow key pressed\n";

                            break;
                        case 'C':
                            std::cout << "Right arrow key pressed\n";

                            break;
                        case 'D':
                            std::cout << "Left arrow key pressed\n";

                            break;
                    }
                }
            } else if (c == 'q') {
                std::cout << "Quit key pressed\n";
                break;
            }
        }
    }
}


void setRawMode(termios &orig_termios) {
    termios raw = orig_termios;
    raw.c_lflag &= ~(ECHO | ICANON);
    raw.c_cc[VMIN] = 0;
    raw.c_cc[VTIME] = 1;
    tcsetattr(STDIN_FILENO, TCSAFLUSH, &raw);
}

// Function to reset the terminal to its original mode
void resetRawMode(const termios &orig_termios) {
    tcsetattr(STDIN_FILENO, TCSAFLUSH, &orig_termios);
}


int main(){

    termios orig_termios;

    // Get the current terminal settings
    tcgetattr(STDIN_FILENO, &orig_termios);

    // Set the terminal to raw mode
    setRawMode(orig_termios);

    std::cout << "Press arrow keys (or 'q' to quit):\n";

    // Detect arrow keys
    detectArrowKeys();

    // Reset the terminal to its original mode
    resetRawMode(orig_termios);

    return 0;
}



