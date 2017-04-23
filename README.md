# enigma
This is a Java implementation of the German Enigma cypher machine used during WWII. The Enigma created a complex polyalphabetic substitution cypher via an elelectromechanical method. The user would input a key, which would be carried as an electrical current through a series of components, resulting in a different letter appearing on the lampboard. Enigma operators would transcribe this output and transmit it using Morse code.

## After pressing a key, the electrical path goes through **these following components**:
* **The plugboard**: Set by the operator, the plugboard swaps specified letter pairs. Typically, 10 pairs would be swapped.
* **The rotors**: Army Enigmas had three rotors, Navy Enigmas had four. Each had a specific wiring which mapped each letter to a different one. Placing rotors in series led to a constantly changing way to encypher each letter. Each rotor had electrical contacts on both sides, connected internally by its wiring.
  * *Rotor turnover*: Enigma rotors moved like an odometer, with a small but significant twist. After every key press, the right rotor moved by one increment. Each rotor had a set turnover spot, at which point it would trigger the next rotor (like an odometer). However, so-called "double stepping" complicated this. If a rotor turned and the rotor to its left was at turnover notch, it would trigger the left rotor.
* **The reflector**: The reflector took an input electrical signal and refleced it back as a different letter.

## Encryption is the same as decryption
After passing through the reflector, the electrical path went back through the rotors, then the plugboard, then to the lampboard. This feature allowed encryption to be the same as decryption. As long as the Enigma decoding cyphertext is setup the same way as the Enigma that encrypted it, it will output the original plaintext.

## Parameters for setting up the Enigma (important for running this program)
* **Rotor order**: The Enigma originally had 5 different rotors (I, II, III, IV, V) of which the operator selected 3. Later, 3 more rotors (VI, VII, VIII) were added.
* **Ring setting**: The position of the internal wirings of each rotor relative to the electrical contacts.
* **Rotor start positions**: The initial orientations of the rotors (which letters show in the window in the top of the machine).
* **Plugboard pairs**: Pairs to be swapped in the plugboard (this added the largest contribution to the number of possibile confiurations, making brute force decryption techniques impossible).

## How this program works
This repository includes Java classes representing each Enigma component (Rotor, Reflector, and Plugboard). Enigma.java is what you should run, as it puts these components together to perform the encryption. It takes command line input.

The file SettingGenerator.java creates random Enigma keys for a specified number of days, and saves them to a text file that you specify in the command line.
