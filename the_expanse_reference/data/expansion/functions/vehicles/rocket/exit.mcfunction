scoreboard players set @s optn_cooldown 40
scoreboard players reset @e[type=armor_stand,tag=rocket,limit=1,sort=nearest] launch_timer
stopsound @s player expansion:rocket.launch
tag @s remove inside_rocket
tag @s remove inside_vehicle
