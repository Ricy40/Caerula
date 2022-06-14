# summon the armor stand that is about to be the spaceship
summon minecraft:armor_stand ~ ~ ~ {Invulnerable:1b,Marker:0b,NoGravity:0b,Invisible:1b,Tags:["exp_spaceship", "armor_storage"],DisabledSlots:4144959}

# run the array cycle function, this function cycles through all the entries in the amror storage to see which entry matches the UUID of the player.
function expansion:vehicles/armor_array_cycle

# either run the armor load or the amror death function depending on the livelyness of the player(this exit function is also called from expansion:global/player_death)
# the load armor function also puts the spaceship model onto the spaceship armor stand.
execute unless score @s exp_death matches 1.. run function expansion:vehicles/armor_load
execute if score @s exp_death matches 1.. run function expansion:vehicles/armor_death

# align the spaceship rotation with the player
data modify entity @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] Rotation[0] set from entity @p Rotation[0]

# clear the blaster trigger item
clear @s minecraft:carrot_on_a_stick{ship_blaster:1b}

# enable the spaceship options
scoreboard players enable @s spaceship_optns

# kill any planetarium markers that may be present
function expansion:vehicles/spaceship/markers/kill_markers

# copy the fuel level from the player to the spaceship armor stand
scoreboard players operation @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] fuel_level = @s fuel_level
scoreboard players operation @e[type=armor_stand,tag=exp_spaceship,limit=1,sort=nearest] fuel_max = @s fuel_max
scoreboard players reset @s fuel_level
scoreboard players reset @s fuel_max

# teleport the player next to the spaceship
execute rotated ~ 0 run tp @s ^-2 ^ ^1

# set the option menu cooldown to 40, to prevent players from instantly opening the menu again when exiting the spaceship.
scoreboard players set @s optn_cooldown 40

# clear all the spaceships effects
effect clear @s minecraft:levitation
effect clear @s minecraft:slow_falling
effect clear @s minecraft:invisibility
effect clear @s minecraft:weakness
effect clear @s minecraft:slowness
effect clear @s minecraft:resistance

# remove the tags from the player
tag @s remove inside_spaceship
tag @s remove inside_vehicle

# remove tags from the spaceship armor stand
tag @e[type=armor_stand,tag=exp_spaceship,tag=armor_storage,limit=1,sort=nearest] remove armor_storage