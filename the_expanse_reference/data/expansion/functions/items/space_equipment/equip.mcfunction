function expansion:handy_tools/armor_tags/helmet_on
title @s subtitle {"text":" "}
title @s[tag=helmet_on] title {"text":"Remove your helmet","color":"gold","bold":true}

item replace entity @s[tag=!helmet_on] armor.head from entity @s weapon.mainhand 
item replace entity @s[tag=!helmet_on] weapon.mainhand with air

scoreboard players set @s oxygen_max 24000
scoreboard players set @s[predicate=expansion:nbt_checks/armor/space_equipment/oxygen] oxygen_max 48000
execute unless entity @s[predicate=expansion:nbt_checks/armor/space_equipment/oxygen] run scoreboard players set @s[scores={oxygen_level=24000..}] oxygen_level 24000

execute unless score @s[tag=!helmet_on] oxygen_level matches -1.. run scoreboard players set @s oxygen_level 24000
tag @s remove helmet_on

playsound minecraft:item.armor.equip_iron player @a ~ ~ ~