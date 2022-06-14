title @p subtitle {"text":" "}
tp @e[type=villager,tag=lunar_villager,limit=2,sort=nearest] ~ ~-360 ~
playsound expansion:lunarmodule.launch player @p ~ ~ ~
particle flame ~ ~2 ~ 0 0 0 0.2 200
particle minecraft:cloud ~ ~2 ~ 0 0 0 0.3 300
title @p subtitle {"text":"Do not leave the lunar module from now on.","color":"gold","bold":true}
execute unless entity @e[type=armor_stand,tag=module_bottom,distance=0.1,limit=1,sort=nearest] run summon armor_stand ~ ~ ~ {Invisible:1b,Invulnerable:1b,Tags:["module_bottom"],ArmorItems:[{},{},{},{id:"minecraft:carrot_on_a_stick",Count:1b,tag:{Unbreakable:1b,CustomModelData:1012020}}]}
data modify entity @s ArmorItems[3].tag.CustomModelData set value 1012021
tag @s add module_top