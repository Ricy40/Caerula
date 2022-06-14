tag @a[tag=generator] remove generator
tag @r add generator
#effect give @a[tag=generator] minecraft:blindness 9999 10 true
execute as @a[tag=generator] at @s run forceload add ~ ~
execute as @a[tag=generator] at @s run summon armor_stand ~ ~ ~ {Tags:["spawn"],NoGravity:1b,Invisible:1b,Marker:1b}

tellraw @a ["",{"text":"Solar System is absent or incomplete, starting system generation in 3 seconds. Please do not interrupt this process."}]

scoreboard players set @a[tag=generator,gamemode=survival] exp_gamemode 0
scoreboard players set @a[tag=generator,gamemode=creative] exp_gamemode 1
scoreboard players set @a[tag=generator,gamemode=spectator] exp_gamemode 2
scoreboard players set @a[tag=generator,gamemode=adventure] exp_gamemode 3
execute as @a[tag=generator] run gamemode spectator

scoreboard players reset #earth tick_distributer
scoreboard players reset #moon tick_distributer
scoreboard players reset #mars tick_distributer
scoreboard players reset #venus tick_distributer
#scoreboard players reset #mercury tick_distributer
scoreboard players reset #jupiter tick_distributer
scoreboard players reset #europa tick_distributer

scoreboard players reset #earth delay
scoreboard players reset #moon delay
scoreboard players reset #mars delay
scoreboard players reset #venus delay
#scoreboard players reset #mercury delay
scoreboard players reset #jupiter delay
scoreboard players reset #europa delay

scoreboard players reset #finish_generation delay

scoreboard players set #earth delay 60