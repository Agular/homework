__author__ = 'Ragnar'

"""reorient_cars(list_of_wagons, depot_length) -
funktsioon, mis v6tab rongi vagunite  listi ja depoo pikkuse
(mitu vagunit korraga mahub ooteplatvormi 22rde seisma) ning
v2ljastab v2ljuva rongi listi, kus on m22ratletud uus vagunite j2rjekord.  """


def reorient_cars(list_of_wagons, depot_length):
    if depot_length <= 0:
        return list_of_wagons
    else:

        out_depot = []

        while list_of_wagons != []:
            parking_depot = []

            while len(parking_depot) != depot_length:
                if list_of_wagons == []:
                    break
                else:
                    parking_depot.append(list_of_wagons[0])
                    list_of_wagons.pop(0)
            parking_depot.reverse()

            for i in range(0, len(parking_depot)):
                reversed_wagon = parking_depot[i][::-1]
                out_depot.append(reversed_wagon)

        return out_depot


#x= reorient_cars(['ab','bc','cd', 'de'],2)
#print (x)
