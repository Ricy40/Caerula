# replace the offhand slot of the spaceship armor stand with my space equipment and remove it's model
execute store result score @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] equipment_cmd run data get entity @s Inventory[{Slot:103b}].tag.CustomModelData
item replace entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] weapon.offhand from entity @s armor.head expansion:utility/remove_model

# store the cmd of the spaceship and change it to the flying version
execute as @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] store result score @s spaceship_cmd run data get entity @s ArmorItems[3].tag.CustomModelData
scoreboard players remove @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] spaceship_cmd 1
execute as @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] store result entity @s ArmorItems[3].tag.CustomModelData int 1 run scoreboard players get @s spaceship_cmd

# put the spaceship in the players head slot
item replace entity @s armor.head from entity @e[type=armor_stand,tag=armor_storage,distance=..3.5,limit=1,sort=nearest] armor.head expansion:spaceship/enter

# make the model on the armor stand invisible
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[3].tag.CustomModelData set value 1012199

# copy over the players other armor and place it in wrong slots to hide them
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[1] set from entity @s Inventory[{Slot:100b}]
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[0] set from entity @s Inventory[{Slot:102b}]
data modify entity @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest] ArmorItems[2] set from entity @s Inventory[{Slot:101b}]

# remove the players armor
item replace entity @s armor.chest with air
item replace entity @s armor.legs with air
item replace entity @s armor.feet with air
