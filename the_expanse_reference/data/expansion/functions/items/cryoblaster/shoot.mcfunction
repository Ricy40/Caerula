tag @s add shooting_ice
function expansion:handy_tools/raycast/gun_cast
execute as @e[tag=smirnoff,type=#expansion:sentient] at @s run function expansion:items/cryoblaster/freeze
scoreboard players remove @s exp_ammo 2
tag @s add used_ammo
tag @s remove shooting_ice

