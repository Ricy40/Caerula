#kill @e[type=area_effect_cloud,tag=railgun_ray]
summon area_effect_cloud ~ ~1.75 ~ {Duration:1,Tags:[railgun_ray]}
tp @e[tag=railgun_ray,limit=1,sort=nearest] @s
execute at @s anchored eyes run tp @e[tag=railgun_ray,limit=1,sort=nearest] ^ ^ ^2
tag @s add shooting
execute as @e[tag=railgun_ray,limit=1,sort=nearest] at @s run function expansion:items/railgun/loop
tag @s remove shooting
tp @s ~ ~ ~ ~ ~-2
scoreboard players set @s exp_cooldown 20
scoreboard players remove @s exp_ammo 1
execute store result storage expansion:ammo data.ammo byte 1 run scoreboard players get @s exp_ammo
item modify entity @s weapon.mainhand expansion:railgun/ammo