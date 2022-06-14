## ----- Run from expansion:vehicles/spaceship/exit ----- ##

# reset the exit_bool scoreboard
scoreboard players reset @s exit_bool

# copy the players UUID to the temporary data storage
data modify storage expansion:player_armor data.temp.UUID set from entity @s UUID

# TRY to copy the UUID just saved into the temporary data storage to the UUID associated with the first entry of the armor data storage. 
# This will fail if both UUID's are the same and then give an exit_bool of 0. 
# This will succeed if the UUID's are different and the give an exit_bool of 1
execute store success score @s exit_bool run data modify storage expansion:player_armor data.armor[0].UUID set from storage expansion:player_armor data.temp.UUID

# if the UUID's were different, then move the first armor entry to the back of the array and run this function again to repeat the process until a match in UUID's is found.
execute if score @s exit_bool matches 1 run data modify storage expansion:player_armor data.armor append from storage expansion:player_armor data.armor[0]
execute if score @s exit_bool matches 1 run data remove storage expansion:player_armor data.armor[0]
execute if score @s exit_bool matches 1 run function expansion:vehicles/armor_array_cycle