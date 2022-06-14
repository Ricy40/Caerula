tp @r[tag=generator] @e[type=armor_stand,tag=spawn,limit=1,sort=nearest]
execute at @e[type=armor_stand,tag=spawn,limit=1] run forceload remove ~ ~
execute if score #earth gen_success matches 1 if score #moon gen_success matches 1 if score #mars gen_success matches 1 if score #venus gen_success matches 1 if score #mercury gen_success matches 1 if score #jupiter gen_success matches 1 if score #europa gen_success matches 1 run scoreboard players set #total_system gen_success 1
effect clear @s minecraft:blindness
schedule function expansion:destinations/space/remove_generator_tag 20t
execute as @s[scores={exp_gamemode=0}] run gamemode survival
execute as @s[scores={exp_gamemode=1}] run gamemode creative
execute as @s[scores={exp_gamemode=2}] run gamemode spectator
execute as @s[scores={exp_gamemode=3}] run gamemode adventure
kill @e[type=armor_stand,tag=spawn]