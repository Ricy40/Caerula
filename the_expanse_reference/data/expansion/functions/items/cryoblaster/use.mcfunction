# get the current ammo of the cryoblaster and store it inside exp_ammo
execute store result score @s exp_ammo run data get entity @s SelectedItem.tag.ammo
execute unless score @s exp_ammo matches 0.. run scoreboard players set @s exp_ammo 0

# placing block
execute if score @s exp_ammo matches 1.. if score @s ice_disp_mode matches 1 run function expansion:items/cryoblaster/place_block
# shooting ice
execute if score @s exp_ammo matches 2.. if score @s ice_disp_mode matches 2 run function expansion:items/cryoblaster/shoot
# place wall
execute if score @s exp_ammo matches 5.. if score @s ice_disp_mode matches 3 run function expansion:items/cryoblaster/place_wall

# reload functions
title @s subtitle {"text":" "}
execute unless entity @s[nbt={Inventory:[{id:"minecraft:water_bucket"}]}] run title @s[tag=!used_ammo] title {"text":"Gather more water","color":"red","bold":true}
execute if entity @s[tag=!used_ammo,nbt={Inventory:[{id:"minecraft:water_bucket"}]}] run function expansion:items/cryoblaster/reload

execute store result storage expansion:ammo data.ammo byte 1 run scoreboard players get @s exp_ammo
item modify entity @s weapon.mainhand expansion:cryoblaster/ammo
tag @s remove used_ammo
