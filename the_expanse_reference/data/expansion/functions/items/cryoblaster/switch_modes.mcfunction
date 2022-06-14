execute store result score @s ice_disp_mode run data get entity @s SelectedItem.tag.mode
scoreboard players add @s ice_disp_mode 1
execute if score @s ice_disp_mode matches 4 run scoreboard players set @s ice_disp_mode 1

execute store result storage expansion:mode data.mode byte 1 run scoreboard players get @s ice_disp_mode

execute if score @s ice_disp_mode matches 1 run item modify entity @s weapon.mainhand expansion:cryoblaster/mode1
execute if score @s ice_disp_mode matches 2 run item modify entity @s weapon.mainhand expansion:cryoblaster/mode2
execute if score @s ice_disp_mode matches 3 run item modify entity @s weapon.mainhand expansion:cryoblaster/mode3