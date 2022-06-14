# clear the data.temp from possible previous data
data remove storage expansion:player_armor data.temp

# Store all the armor data and the landed spaceship data inside a temporary storage
data modify storage expansion:player_armor data.temp.spaceship set from entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[3]
data modify storage expansion:player_armor data.temp.helmet set from entity @s Inventory[{Slot:103b}]
data modify storage expansion:player_armor data.temp.chestplate set from entity @s Inventory[{Slot:102b}]
data modify storage expansion:player_armor data.temp.leggings set from entity @s Inventory[{Slot:101b}]
data modify storage expansion:player_armor data.temp.boots set from entity @s Inventory[{Slot:100b}]

# store the cmd of the spaceship and change it to the flying version
execute as @e[type=armor_stand,tag=armor_storage,distance=..3.5,limit=1,sort=nearest] run function expansion:vehicles/decrease_model
scoreboard players operation @s spaceship_cmd = @e[type=armor_stand,tag=armor_storage,distance=..3.5,limit=1,sort=nearest] spaceship_cmd

# replace the players head armor with the flying version of the spaceship
item replace entity @s armor.head from entity @e[type=armor_stand,tag=armor_storage,distance=..3.5,limit=1,sort=nearest] armor.head expansion:spaceship/enter

# copy the data from the temporary storage to the armor storage
data modify storage expansion:player_armor data.armor prepend from storage expansion:player_armor data.temp

# copy the players UUID to a UUID field inside this particular armor storage, this is used to later link the correct storage to the correct player in the case of multiplayer
data modify storage expansion:player_armor data.armor[0].UUID set from entity @s UUID

# clear temp
data remove storage expansion:player_armor data.temp

# remove all the other armor from the player
item replace entity @s armor.chest with air
item replace entity @s armor.legs with air
item replace entity @s armor.feet with air