# add tags to the player to be able to detect if they are inside the spaceship, 
tag @s add inside_spaceship
tag @s add inside_vehicle

# remove tags from the player, choosing_optn regulates the option menu. rising and falling regulate the zero gravity environment
tag @s remove choosing_optn
tag @s remove rising
tag @s remove falling

# add the armor_storage tag to the spaceship to be able to distinct this spaceship from any other spaceships nearby
tag @e[type=armor_stand,tag=exp_spaceship,distance=..3.5,limit=1,sort=nearest] add armor_storage

# teleport the player to the position of the spaceship
execute rotated ~ 0 at @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,distance=..3.5,limit=1,sort=nearest] run tp @s ~ ~ ~ ~ ~

# save the players armor inside a very epic data storage, this is necessary because the player needs to become invisible when they enter the spaceship
function expansion:vehicles/armor_save

# give the player the blaster trigger item, but only if they have no item in their offhand to prevent it from being replaced
execute unless entity @s[nbt={Inventory:[{Slot:-106b}]}] run item replace entity @s weapon.offhand with minecraft:carrot_on_a_stick{display:{Name:'{"text":"Fire!","color":"white","bold":false,"italic":false,"underlined":false}'},Unbreakable:1b,CustomModelData:1012006,ship_blaster:1b} 1

# copy the fuel level from the spaceship armor stand to the player
scoreboard players operation @s fuel_level = @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] fuel_level
scoreboard players set @s fuel_max 256

# kill the spaceship armor stand
kill @e[type=armor_stand,tag=armor_storage,limit=1,sort=nearest]
