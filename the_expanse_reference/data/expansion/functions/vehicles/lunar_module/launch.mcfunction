execute as @p[predicate=!expansion:nbt_checks/armor/space_equipment/equipment] run function expansion:handy_tools/error_messages/equip_space_equipment
execute unless score @s launch_timer matches 0.. if entity @p[predicate=expansion:nbt_checks/armor/space_equipment/equipment] run scoreboard players set @s launch_timer 230