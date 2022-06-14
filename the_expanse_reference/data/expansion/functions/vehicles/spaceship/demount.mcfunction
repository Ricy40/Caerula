# store the fuel level inside the spaceship item
execute store result entity @s ArmorItems[3].tag.fuel_lvl int 1 run scoreboard players get @s fuel_level

# substract one from the spaceship custom model data so it changes to the flying version
function expansion:vehicles/decrease_model

# spawn the placeholder item and copy all the spaceship data to it
loot spawn ~ ~ ~ loot expansion:vehicles/spaceship
data modify entity @e[type=item,nbt={Item:{tag:{exp_spaceship:1b}}},limit=1,sort=nearest] Item set from entity @s ArmorItems[3]

# cosmetics
playsound block.anvil.use block @a ~ ~ ~
scoreboard players enable @s spaceship_optns
kill @s