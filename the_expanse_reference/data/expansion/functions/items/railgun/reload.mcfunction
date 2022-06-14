scoreboard players set @s exp_ammo 2
scoreboard players set @s exp_cooldown 20

execute store result storage expansion:ammo data.ammo byte 1 run scoreboard players get @s exp_ammo
item modify entity @s weapon.mainhand expansion:railgun/ammo

clear @s minecraft:iron_ingot 1
playsound expansion:railgun.reload player @a ~ ~ ~