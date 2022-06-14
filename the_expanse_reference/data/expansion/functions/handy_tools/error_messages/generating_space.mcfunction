execute as @s run scoreboard players add @s title_timer 1
execute as @s[scores={title_timer=10}] run title @s actionbar [{"translate":"exp_screentxt_genspace_actionbar","color":"red","bold":true}]
execute as @s[scores={title_timer=20}] run title @s actionbar [{"translate":"exp_screentxt_genspace_actionbar","color":"red","bold":true},{"text":"."}]
execute as @s[scores={title_timer=30}] run title @s actionbar [{"translate":"exp_screentxt_genspace_actionbar","color":"red","bold":true},{"text":".."}]
execute as @s[scores={title_timer=40}] run title @s actionbar [{"translate":"exp_screentxt_genspace_actionbar","color":"red","bold":true},{"text":"..."}]
execute as @s[scores={title_timer=40..}] run scoreboard players set @s title_timer 0
