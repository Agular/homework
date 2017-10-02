__author__ = 'Ragnar'
def encode(message,shift):
    chars_message = []
    encoded_message = ""

    if shift>=26:
        shift-=26*(shift//26)
        print (shift)

    for i in message:                         #muudan teksti numbriteks
        chars_message.append(ord(i))

    for i in chars_message:
        if 65 <= i <= 90:                       #kontrollib, kas on suur t2ht
            if (i+shift) > 90:
                uus_char = 64 + ((i+shift)-90)
                encoded_message += chr(uus_char)
            else:
                encoded_message += chr(i+shift)

        elif 97 <= i <= 122:                #kontrollib, kas on v2ike t2ht
            if (i+shift) > 122:
                uus_char = 96 + ((i+shift)-122)
                encoded_message += chr(uus_char)
            else:
                encoded_message += chr(i+shift)

        else:                                   #kui on muu m2rk, siis lihtsalt lisab selle
            encoded_message += chr(i)
    return encoded_message


def crack(encoded_message,phrase):

    chars_encoded_message = []
    for i in encoded_message:                   # muudan t2hed numbriteks
        chars_encoded_message.append(ord(i))

    for shift in range(0, 26):                # hakkan proovima erinevaid nihkeid
        decoded_message=""

        for i in chars_encoded_message:
            if 65 <= i <= 90:                   # kui m2rk on suur t2ht
                if(i-shift)<65:
                    uus_char=91-(65-(i-shift ))
                    decoded_message += chr(uus_char)
                else:
                    decoded_message+= chr(i-shift)


            elif 97 <= i <= 122:                # kui m2rk on v2ike t2ht
                if(i-shift)<97:
                    uus_char=123-(97-(i-shift ))
                    decoded_message +=chr(uus_char)
                else:
                    decoded_message += chr(i-shift)

            else:
                decoded_message+=chr(i)         # kui m2rk on muu m2rk

        if phrase in decoded_message:
            return decoded_message
            break
